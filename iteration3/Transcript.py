class Transcript:
    def __init__(self, student):
        self.__studentCourses = []
        self.__student = student
        self.__semester = student.getSemester()
        self.__cgpa = self.calculateCgpa()
        self.__takenCredits = self.calculateTakenCredits()
        self.__completedCredits = self.calculateCompletedCredits()
        self.__courseGradeMap = {}

    def calculateValues(self):
        self.__cgpa = self.calculateCgpa()
        self.__takenCredits = self.calculateTakenCredits()
        self.__completedCredits = self.calculateCompletedCredits()

    def calculateCgpa(self):
        if not self.__studentCourses:
            return 0
        totalGrade = 0
        totalCredits = 0
        for course in self.__studentCourses:
            grades = self.__courseGradeMap.get(course, [])
            if grades and len(grades) > 0:
                grade = grades[-1].getGradeOver4()
                credit = course.getCourseCredit()
                if grade is not None and credit is not None:
                    totalGrade += grade * credit
                    totalCredits += credit

        if totalCredits == 0:
            return 0

        self.__cgpa = totalGrade / totalCredits
        return self.__cgpa

    def calculateTakenCredits(self):
        totalCredits = sum(course.getCourseCredit() for course in self.__studentCourses)
        self.__takenCredits = totalCredits
        return totalCredits

    def calculateCompletedCredits(self):
        self.__completedCredits = 0
        for course in self.__studentCourses:
            grades = self.__courseGradeMap.get(course)
            lastGrade = grades[-1] if grades else None
            if lastGrade and lastGrade.getLetterGrade() not in ["FF", "FD"]:
                self.__completedCredits += course.getCourseCredit()
        return self.__completedCredits

    def showTranscript(self):
        print(f'Transcript for {self.__student.getName()} {self.__student.getSurname()}:')
        print(f'Student ID       : {self.__student.getID()}')
        print(f'CGPA             : {self.__cgpa:.2f}')
        print(f'Completed Credits: {self.calculateCompletedCredits()}')
        print(f'Taken Credits    : {self.calculateTakenCredits()}')
        print(f'Semester         : {self.__semester}')
        print(f'Department       : {self.__student.getDepartment().getDepartmentName()}')

        for course in self.__studentCourses:
            for grade in self.__courseGradeMap.get(course, []):
                if grade is None:
                    print(
                        "%-15s%-45s%-15s%-15s" % (course.getCourseCode(), course.getCourseName(), course.getCourseCredit(), "--"))
                else:
                    print("%-15s%-45s%-15s%-15s" % (
                        course.getCourseCode(), course.getCourseName(), course.getCourseCredit(), grade.getLetterGrade()))
        print()

    def courseStatusCheck(self):
        successfulCourses = []
        failedCourses = []
        ongoingCourses = []

        for course in self.__studentCourses:
            grades = self.__courseGradeMap.get(course, [])
            for grade in grades:
                if grade is None:
                    ongoingCourses.append(course)
                elif grade.getLetterGrade() in ["FF", "FD"]:
                    failedCourses.append(course)
                else:
                    successfulCourses.append(course)

        print("Successful Courses:\n")
        for course in successfulCourses:
            print(course.getCourseCode() + " " + course.getCourseName())
        print()

        print("Failed Courses:\n")
        for course in failedCourses:
            print(course.getCourseCode() + " " + course.getCourseName())
        print()

        print("Ongoing Courses:\n")
        for course in ongoingCourses:
            print(course.getCourseCode() + " " + course.getCourseName())
        print()

    def getStudentCourses(self):
        return self.__studentCourses

    def setStudentCourses(self, studentCourses):
        self.__studentCourses = studentCourses

    def getTakenCredits(self):
        return self.__takenCredits

    def setTakenCredits(self, takenCredits):
        self.__takenCredits = takenCredits

    def getCompletedCredits(self):
        return self.__completedCredits

    def getCgpa(self):
        return self.__cgpa

    def setStudent(self, student):
        self.__student = student

    def getCourseGradeMap(self):
        return self.__courseGradeMap

    def setCourseGradeMap(self, courseGradeMap):
        self.__courseGradeMap = courseGradeMap
