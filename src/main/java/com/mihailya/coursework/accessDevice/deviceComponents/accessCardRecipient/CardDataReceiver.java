package com.mihailya.coursework.accessDevice.deviceComponents.accessCardRecipient;

import com.mihailya.coursework.accessDevice.data.entities.AccessCard;

public interface CardDataReceiver {
	void onCardInserted(AccessCard accessCard);
}
