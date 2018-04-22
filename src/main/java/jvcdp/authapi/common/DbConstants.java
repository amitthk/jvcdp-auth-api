package jvcdp.authapi.common;

public final class DbConstants {
	public final static String SEL= "SELECT id,name,userName,email,passwordHash,salt,createDate,lastLogin,lastUpdate,projectId FROM userinfo";
	public final static String UPDATE= "UPDATE userinfo SET name = #{name},userName = #{userName},email = #{email},passwordHash = #{passwordHash},salt = #{salt},createDate = #{createDate},lastLogin = #{lastLogin},lastUpdate = #{lastUpdate},projectId = #{projectId} WHERE id = #{id}";
	public final static String INSERT= "INSERT INTO userinfo (name,userName,email,passwordHash,salt,createDate,lastLogin,lastUpdate,projectId) VALUES (#{name},#{userName},#{email},#{passwordHash},#{salt},#{createDate},#{lastLogin},#{lastUpdate},#{projectId})";
	public final static String FIND= "SELECT id,name,userName,email,passwordHash,salt,createDate,lastLogin,lastUpdate,projectId FROM userinfo WHERE ID = #{value}";
	public final static String DEL= "DELETE FROM userinfo WHERE ID = #{value}";
	public static final String FINDBYEMAIL = "SELECT TOP 1 id,name,userName,email,passwordHash,salt,createDate,lastLogin,lastUpdate,projectId FROM userinfo WHERE email = #{value}";
}
