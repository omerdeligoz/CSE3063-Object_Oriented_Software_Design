class Transcript:
    def __init__(self, student):
        self.studentCourses = []
        self.student = student
        self.semester = student.semester
        self.cgpa = self.calculateCgpa()
        self.takenCredits = self.calculateTakenCredits()
        self.completedCredits = self.calculateCompletedCredits()
        self.courseGradeMap = {}

    def calculateValues(self):
        self.cgpa = self.calculateCgpa()
        self.takenCredits = self.calculateTakenCredits()
        self.completedCredits = self.calculateCompletedCredits()

    def calculateCgpa(self):
        if not self.studentCourses:
            return 0
        totalGrade = 0
        totalCredits = 0
        for course in self.studentCourses:
            if len(self.courseGradeMap.get(course)) > 0:
                totalGrade += self.courseGradeMap.get(course)[-1].gradeOver4 * course.courseCredit
                totalCredits += course.courseCredit

        if totalCredits == 0:
            return 0

        self.cgpa = totalGrade / totalCredits
        return self.cgpa

    def calculateTakenCredits(self):
        totalCredits = sum(course.courseCredit for course in self.studentCourses)
        self.takenCredits = totalCredits
        return totalCredits

    def calculateCompletedCredits(self):
        self.completedCredits = 0
        for course in self.studentCourses:
            lastGrade = self.courseGradeMap.get(course)[-1] if self.courseGradeMap.get(course) else None
            if lastGrade and lastGrade.letterGrade not in ["FF", "FD"]:
                self.completedCredits += course.courseCredit
        return self.completedCredits

    def showTranscript(self):
        print(f'Transcript for {self.student.name} {self.student.surname}:')
        print(f'Student ID       : {self.student.ID}')
        print(f'CGPA             : {self.cgpa:.2f}')
        print(f'Completed Credits: {self.calculateCompletedCredits()}')
        print(f'Taken Credits    : {self.calculateTakenCredits()}')
        print(f'Semester         : {self.semester}')
        print(f'Department       : {self.student.department.departmentName}')

        for course in self.studentCourses:
            for grade in self.courseGradeMap.get(course, []):
                if grade is None:
                    print("%-15s%-45s%-15s%-15s" % (course.courseCode, course.courseName, course.courseCredit, "--"))
                else:
                    print("%-15s%-45s%-15s%-15s" % (
                        course.courseCode, course.courseName, course.courseCredit, grade.letterGrade))
        print()

    def courseStatusCheck(self):
        successfulCourses = []
        failedCourses = []
        ongoingCourses = []

        for course in self.studentCourses:
            grades = self.courseGradeMap.get(course, [])
            for grade in grades:
                if grade is None:
                    ongoingCourses.append(course)
                elif grade.letterGrade in ["FF", "FD"]:
                    failedCourses.append(course)
                else:
                    successfulCourses.append(course)

        print("Successful Courses:\n")
        for course in successfulCourses:
            print(course.courseCode + " " + course.courseName)
        print()

        print("Failed Courses:\n")
        for course in failedCourses:
            print(course.courseCode + " " + course.courseName)
        print()

        print("Ongoing Courses:\n")
        for course in ongoingCourses:
            print(course.courseCode + " " + course.courseName)
        print()
