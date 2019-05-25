package com.mihailya.coursework.accessDevice.deviceComponents.accessCardRecipient;

import com.mihailya.coursework.accessDevice.data.entities.AccessCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AccessCardRecipient {

	private Logger log = LoggerFactory.getLogger(AccessCardRecipient.class.getName());
	private AccessCard insertedCard;
	private ICardDataReceiver cardDataReceiver;

	public AccessCardRecipient(ICardDataReceiver cardDataReceiver) {
		this.cardDataReceiver = cardDataReceiver;
	}

	public void insertCard(AccessCard insertedCard) {
		this.insertedCard = insertedCard;
		cardDataReceiver.onCardInserted(insertedCard);
	}

	public AccessCard getInsertedCard() {
		return insertedCard;
	}

	public void lockCard() {
		log.debug("Lock card: " + insertedCard);
		insertedCard.setLocked(true);
	}

	public void returnCard() {
		log.debug("Return card: " + insertedCard);
		insertedCard = null;
	}
}
