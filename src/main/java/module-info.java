module br.com.ifsp.nando.gerenciadortarefasescolares {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;


    opens br.com.ifsp.nando.gerenciadortarefasescolares.modelo to org.hibernate.orm.core;

    opens br.com.ifsp.nando.gerenciadortarefasescolares.controlador to javafx.fxml;
    opens br.com.ifsp.nando.gerenciadortarefasescolares.view to javafx.graphics;
    opens br.com.ifsp.nando.gerenciadortarefasescolares.teste to javafx.graphics;
    exports br.com.ifsp.nando.gerenciadortarefasescolares;
}