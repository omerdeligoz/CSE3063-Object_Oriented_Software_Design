class Grade:
    def __init__(self, letterGrade):
        self.letterGrade = letterGrade
        self.gradeOver4 = self.setGradeOver4()
        self.gradeOver100 = self.setGradeOver100()

    def setGradeOver100(self):
        return {
            'AA': 100,
            'BA': 90,
            'BB': 85,
            'CB': 75,
            'CC': 70,
            'DC': 65,
            'DD': 60,
            'FD': 50,
            'FF': 0
        }.get(self.letterGrade, 0)  # 0 is default if grade not found

    def setGradeOver4(self):
        return {
            'AA': 4.0,
            'BA': 3.5,
            'BB': 3.0,
            'CB': 2.5,
            'CC': 2.0,
            'DC': 1.5,
            'DD': 1.0,
            'FD': 0.5,
            'FF': 0.0
        }.get(self.letterGrade, 0)  # 0 is default if grade not found
