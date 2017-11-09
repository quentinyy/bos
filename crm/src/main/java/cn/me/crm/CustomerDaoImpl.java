package cn.me.crm;

import cn.me.crm.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.jws.WebService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebService(endpointInterface = "cn.me.crm.ICustomerDao")
public class CustomerDaoImpl implements ICustomerDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Customer> findCustomerNotAssociate() {
        String sql = "select * from t_customer where decidedzone_id is NULL";
        List<Customer> customerList = jdbcTemplate.query(sql, new RowMapper<Customer>() {

            @Override
            public Customer mapRow(ResultSet rs, int i) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String station = rs.getString("station");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String decidedzone_id = rs.getString("decidedzone_id");
                return new Customer(id, name, station, telephone, address, decidedzone_id);
            }
        });
        return customerList;
    }

    @Override
    public List<Customer> findCustomerAssociate(String decidedzzone_id) {
        String sql = "select * from t_customer where decidedzone_id =?";
        List<Customer> customerList = jdbcTemplate.query(sql, new RowMapper<Customer>() {

            @Override
            public Customer mapRow(ResultSet rs, int i) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String station = rs.getString("station");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String decidedzone_id = rs.getString("decidedzone_id");
                return new Customer(id, name, station, telephone, address, decidedzone_id);
            }
        },decidedzzone_id);
        return customerList;
    }

    @Override
    public void associate(String id, String[] customerIds) {
        String sql = "update t_customer SET decidedzone_id = NULL WHERE decidedzone_id = ?";
        jdbcTemplate.update(sql,id);
        String sql2 = "update t_customer set decidedzone_id = ? where id = ?";
        for (String cid :customerIds
             ) {
            jdbcTemplate.update(sql2,id,cid);
        }
    }

    @Override
    public Customer findCustomerByTelephone(String telephone) {
        String sql = "select * from t_customer WHERE telephone = ?";
        List<Customer> customerList = jdbcTemplate.query(sql, new RowMapper<Customer>() {

            @Override
            public Customer mapRow(ResultSet rs, int i) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String station = rs.getString("station");
                String telephone = rs.getString("telephone");
                String address = rs.getString("address");
                String decidedzone_id = rs.getString("decidedzone_id");
                return new Customer(id, name, station, telephone, address, decidedzone_id);
            }
        },telephone);
        if(customerList!=null){
            return customerList.get(0);
        }
        return null;
    }

    @Override
    public String findDecidedzoneByAdress(String adress) {
        String sql = "select decidedzone_id from t_customer WHERE address = ?";
        String s = jdbcTemplate.queryForObject(sql, String.class, adress);
        return s;
    }
}
