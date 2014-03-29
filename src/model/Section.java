package model;

/**
 * Created by helifab on 28.03.2014.
 */
public class Section {
    private int ageLimit;
    private String name;
    private int capacity;

    public Section(int ageLimit, String name, int capacity) {
        this.ageLimit = ageLimit;
        this.name = name;
        this.capacity = capacity;
    }

    public Section(int ageLimit){
        this.ageLimit = ageLimit;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;

        Section section = (Section) o;

        if (ageLimit != section.ageLimit) return false;
        if (capacity != section.capacity) return false;
        if (!name.equals(section.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ageLimit;
        result = 31 * result + name.hashCode();
        result = 31 * result + capacity;
        return result;
    }
}
