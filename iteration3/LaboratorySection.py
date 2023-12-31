class LaboratorySection:

    def __init__(self, laboratorySectionCode, capacity, hour, day):
        self.laboratorySectionCode = laboratorySectionCode
        self.capacity = capacity
        self.hour = hour
        self.day = day
        self.assistant = None
        self.numberOfStudents = 0