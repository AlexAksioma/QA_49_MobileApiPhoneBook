package models;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(lastName, contact.lastName) && Objects.equals(email, contact.email) && Objects.equals(phone, contact.phone) && Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, email, phone, address);
    }
}


