/**
 * Represents a contact in the Address Book.
 * Contains details like name, phone, and email.
 * Implements Comparable to allow sorting by name.
 */
public class Contact implements Comparable<Contact> {
    
    private String name;
    private String phone;
    private String email;

    /**
     * Constructor to initialize a new Contact.
     * @param name The name of the contact
     * @param phone The phone number of the contact
     * @param email The email address of the contact
     */
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Gets the contact's name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the contact's name.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the contact's phone number.
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the contact's phone number.
     * @param phone the new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the contact's email address.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact's email address.
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Compares this contact with another based on the name.
     * This is used by Collections.sort() for sorting.
     */
    @Override
    public int compareTo(Contact other) {
        return this.name.compareToIgnoreCase(other.getName());
    }

    /**
     * Returns a string representation of the contact.
     */
    @Override
    public String toString() {
        return "Contact [Name=" + name + ", Phone=" + phone + ", Email=" + email + "]";
    }
}
