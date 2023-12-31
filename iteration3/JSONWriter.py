import json
import logging


class JSONWriter:
    def __init__(self):
        self.__department = None

    def start(self, university):
        for department in university.departments:
            self.__department = department
            self.writeJson()

    def writeJson(self):
        logging.info("Writing JSON data for the department")
        self.writeStudents()
        self.writeRequests()
        self.writeTranscripts()
        logging.info("JSON data for the department has been written")

    def writeTranscripts(self):
        for student in self.__department.students:
            filepath = f"jsons/Transcripts/{student.__ID}.json"
            try:
                with open(filepath, 'r+', encoding='utf-8') as f:
                    data = json.load(f)
                    data['takenCredits'] = student.transcript.takenCredits
                    data['completedCredits'] = student.transcript.completedCredits
                    data['cgpa'] = round(student.transcript.cgpa, 2)
                    data['courses'] = [
                        {
                            "courseCode": course.courseCode,
                            "letterGrade": grade.__letterGrade if grade else None
                        }
                        for course, grades in student.transcript.courseGradeMap.items()
                        for grade in grades
                    ]

                with open(filepath, "w") as f:
                    json.dump(data, f, indent=4)
            except Exception as e:
                print(f"There is a problem with the {student.__ID}.json file")
                logging.error(f"There is a problem with the {student.__ID}.json file")
                exit(0)

    def writeRequests(self):
        try:
            requests_data = []
            for student in self.__department.students:
                if student.hasRequest:
                    request = {
                        "studentID": student.__ID,
                        "courses": [course.courseCode for course in student.draft]
                    }
                    requests_data.append(request)

            with open("jsons/requests.json", "w") as f:
                json.dump(requests_data, f, indent=4)
        except Exception as e:
            print("There is a problem with the requests.json file")
            logging.error("There is a problem with the requests.json file")
            exit(0)

    def writeStudents(self):
        filepath = "jsons/students.json"
        try:
            # load existing data
            with open(filepath, 'r', encoding='utf-8') as f:
                existingData = json.load(f)

            for data in existingData:
                student = self.__department.studentIDStudentMap.get(data["studentID"])
                if student:
                    data["hasRequest"] = student.hasRequest
                    data["labSections"] = [labSection.laboratorySectionCode for labSection in student.labSections]

            # write back updated data
            with open(filepath, "w") as f:
                json.dump(existingData, f, indent=4, ensure_ascii=False)
        except Exception as e:
            print("There is a problem with the students.json file")
            logging.error("There is a problem with the students.json file")
            exit(0)
