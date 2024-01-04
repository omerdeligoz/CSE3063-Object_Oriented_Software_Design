import logging

from iteration3.Advisor import Advisor
from iteration3.Student import Student


class Notification:
    def __init__(self, recipient, message):
        self.recipient = recipient
        self.message = message

    def sendNotification(self):
        if isinstance(self.recipient, Advisor):
            self.recipient.setNotification(self)
        elif isinstance(self.recipient, Student):
            logging.info(f"Notification sent to student {self.recipient.getID()} that their request has been rejected.")
            self.recipient.setNotification(self)

    def getMessage(self):
        return self.message
