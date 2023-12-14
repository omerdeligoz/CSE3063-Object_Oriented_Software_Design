package iteration2;

/**
 * The LaboratorySection class represents a course with a laboratory offered by an educational institution in different departments.
 * It extends course class.
 * It contains extra information about the laboratorySectionCode, capacity, assistant and students of the section.
 */
public class LaboratorySection {
    private String laboratorySectionCode;
    private Assistant assistant;

    private int capacity;

    private int numberOfStudents;

    private int hour;
    private String day;

    /**
     * Creates a new LaboratorySection object with the given parameters
     * Calls the course class's constructor
     *
     * @param laboratorySectionCode the section's code
     * @param capacity              the section's capacity
     */

    public LaboratorySection(String laboratorySectionCode, int capacity, int hour, String day) {
        this.laboratorySectionCode = laboratorySectionCode;
        this.capacity = capacity;
        this.hour = hour;
        this.day = day;
    }

    public String getLaboratorySectionCode() {
        return laboratorySectionCode;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
}
