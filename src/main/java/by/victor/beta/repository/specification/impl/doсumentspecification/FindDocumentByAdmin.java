package by.victor.beta.repository.specification.impl.doсumentspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindDocumentByAdmin implements Specification {
    private String SELECT_DOCUMENT = "SELECT document.user_id,document.iddocument,document.admin_check_id,document.file,user.username as user," +
            "admin.username as admin " +
            "FROM document JOIN user on document.user_id = user.global_id " +
            "left join user as admin on document.admin_check_id=admin.global_id " +
            "where admin.username=?;";
    private String admin;

    public FindDocumentByAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_DOCUMENT);
        ps.setString(1, admin);
        return ps;


    }
}
