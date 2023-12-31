import logging

from Advisor import Advisor


class Notification:
    def __init__(self, recipient, message):
        self.recipient = recipient
        self.message = message
        self.logger = logging.getLogger('Notification')

    def sendNotification(self):
        if isinstance(self.recipient, Advisor):
            self.recipient.setNotification(self)
        elif isinstance(self.recipient, Student):
            self.logger.info(f"Notification sent to student {self.recipient.id} that their request has been rejected.")
            self.recipient.setNotification(self)

    def getMessage(self):
        return self.message