package br.com.hbsis.openweather.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Represents a given city on the world.
 */
@Entity
@Table(name = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    //TODO: validate if it's best to use a JPA entity or a HashMap

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "city_seq")
    @SequenceGenerator(name = "city_seq", sequenceName = "city_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "name")
    @Size(max = 255)
    private String name;

    @Column(name = "country_code")
    @Size(max = 2)
    private String countryCode;

    /**
     * Constructs a city with name and the country code.
     *
     * @param name        city name
     * @param countryCode country code
     */
    public City(@Size(max = 255) String name, @Size(max = 2) String countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }
}
