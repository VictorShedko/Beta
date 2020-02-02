package by.victor.beta.repository.specification.impl.doсumentspecification;

import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindDocumentByUsernameSpecification implements Specification {
    private String SELECT_DOCUMENT = "SELECT document.user_id,document.iddocument,document.admin_check_id,document.file_path,user.username as user," +
            "admin.username as admin " +
            "FROM document JOIN user on document.user_id = user.global_id " +
            "left join user as admin on document.admin_check_id=admin.global_id " +
            "where user.username=?;";
    private String username;

    public FindDocumentByUsernameSpecification(String executorName) {
        this.username = executorName;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SELECT_DOCUMENT);
        ps.setString(1, username);
        return ps;
    }

}
