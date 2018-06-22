package org.student.portalstypendialny.model;

import lombok.*;

import java.util.GregorianCalendar;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Podanie {


    @Setter
    @NonNull
    private GregorianCalendar data;

    @Setter
    private String uzasadnieniePodania;

    @NonNull
    private double sredniaOcen;

    @Setter
    private List<String> listaZalacznikow;

}
