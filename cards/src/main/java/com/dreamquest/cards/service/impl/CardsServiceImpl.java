package com.dreamquest.cards.service.impl;

import ch.qos.logback.core.CoreConstants;
import com.dreamquest.cards.Constants.CardsConstants;
import com.dreamquest.cards.dto.CardsDto;
import com.dreamquest.cards.entity.Cards;
import com.dreamquest.cards.exception.CardsAlreadyExistsException;
import com.dreamquest.cards.exception.ResourceNotFoundException;
import com.dreamquest.cards.mapper.CardsMapper;
import com.dreamquest.cards.repository.CardsRepository;
import com.dreamquest.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    CardsRepository cardsRepository;


    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {



        Optional<Cards> optionalCards= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardsAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }

        cardsRepository.save(createNewCards(mobileNumber));
    }

    private Cards createNewCards(String mobileNumber) {

        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(

                () -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber)


        );

        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {

        boolean isUpdated = false;


        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Card Number", cardsDto.getCardNumber())
        );

        if(cards != null)
        {
            cardsRepository.save(CardsMapper.mapToCards(cardsDto, cards));
            isUpdated = true;
        }


        return isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {

        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(

                () -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber)


        );
        cardsRepository.deleteById(cards.getCardID());
        return true;
    }
}
