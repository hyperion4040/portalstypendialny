package org.student.portalstypendialny.przedmiot;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@RequiredArgsConstructor
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Przedmiot")
public class Przedmiot {





   /* @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;*/

    @Id
    @NonNull
    private String nazwaPrzedmiotu;

    @Setter
    private double ocenaWyklad;

    @Setter
    private double ocenaCwiczenia;


}


