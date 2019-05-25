package com.mihailya.coursework.accessDevice.deviceComponents.accessCardRecipient;

import com.mihailya.coursework.accessDevice.data.entities.AccessCard;

public interface ICardDataReceiver {
	void onCardInserted(AccessCard accessCard);
}
