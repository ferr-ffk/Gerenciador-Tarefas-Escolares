module br.com.ifsp.nando.gerenciadortarefasescolares {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;


    opens br.com.ifsp.nando.gerenciadortarefasescolares to javafx.fxml;
    opens modelo to org.hibernate.orm.core;
    exports br.com.ifsp.nando.gerenciadortarefasescolares;
}