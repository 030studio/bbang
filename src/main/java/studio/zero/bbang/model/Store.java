package studio.zero.bbang.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String storePhoneNumber;
    private String BusinessRegistrationNum;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Point location;
}
