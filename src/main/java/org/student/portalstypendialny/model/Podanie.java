package org.student.portalstypendialny.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Podanie {


    @Setter
    @NonNull
    private Date data;

    @Setter
    private String uzasadnieniePodania;

    @NonNull
    private double sredniaOcen;

    @Setter
    private List<String> listaZalacznikow;

}
