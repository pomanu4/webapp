package usrer.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Connector {

	private String url = "jdbc:mysql://localhost:3306/testdb";
	private String name = "root";
	private String password = "root";

	Connection conect;

	public Connector() {
		try {
			this.conect = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			System.out.println("exception in class connector");
			e.printStackTrace();
		}
	}

	public void saveToDb(String... args) {
		try {
			java.sql.PreparedStatement statement = conect
					.prepareStatement("INSERT INTO USERS (name, id) VALUES('" + args[0] + "', '" + args[1] + "')");
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		try {
			conect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> selectMethod() {
		List<String> info = new LinkedList<>();
		try {
			PreparedStatement statement = conect.prepareStatement("SELECT * FROM users");
			statement.executeQuery();
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				String userInfo = resultSet.getString(1) + " " + resultSet.getString(2)+ " " + resultSet.getString(3);
				info.add(userInfo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

}
