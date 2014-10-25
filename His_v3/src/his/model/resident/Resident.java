
package his.model.resident;

import his.model.ModelClass;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Patrick
 */
public class Resident extends ModelClass {
    
    private final StringProperty name;
    private final StringProperty address;
    private final LongProperty birthday;
    private final StringProperty phone;
    private final StringProperty email;
    
    private final BooleanProperty hoene;
    private final BooleanProperty reserve;
    private final BooleanProperty oneOne;
    
    private final StringProperty barcode;
    private final LongProperty expirationDate;

    public Resident(int id, long createDate, String name, String address, long birthday, String phone, String email, boolean hoene, boolean reserve, boolean oneOne, String barcode, long expirationDate) {
        super(id, createDate);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.birthday = new SimpleLongProperty(birthday);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        
        this.hoene = new SimpleBooleanProperty(hoene);
        this.reserve = new SimpleBooleanProperty(reserve);
        this.oneOne = new SimpleBooleanProperty(oneOne);
        
        this.barcode = new SimpleStringProperty(barcode);
        this.expirationDate = new SimpleLongProperty(expirationDate);
    }
    
    public String getName() {
        return name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getAddress() {
        return address.get();
    }
    
    public void setAddress(String address) {
        this.address.set(address);
    }
    
    public long getBirthday() {
        return birthday.get();
    }
    
    public void setBirthday(long birthday) {
        this.birthday.set(birthday);
    }
    
    public String getPhone() {
        return phone.get();
    }
    
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public boolean isHoene() {
        return hoene.get();
    }
    
    public void setHoene(boolean hoene) {
        this.hoene.set(hoene);
    }
    
    public boolean isReserve() {
        return reserve.get();
    }
    
    public void setReserve(boolean reserve) {
        this.reserve.set(reserve);
    }
    
    public boolean isOneOne() {
        return oneOne.get();
    }
    
    public void setOneOne(boolean oneOne) {
        this.oneOne.set(oneOne);
    }
    
    public String getBarcode() {
        return barcode.get();
    }
    
    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }
    
    public long getExpirationDate() {
        return expirationDate.get();
    }
    
    public void setExpirationDate(long expirationDate) {
        this.expirationDate.set(expirationDate);
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public StringProperty getAddressProperty() {
        return address;
    }

    public LongProperty getBirthdayProperty() {
        return birthday;
    }

    public StringProperty getPhoneProperty() {
        return phone;
    }

    public StringProperty getEmailProperty() {
        return email;
    }

    public BooleanProperty getHoeneProperty() {
        return hoene;
    }

    public BooleanProperty getReserveProperty() {
        return reserve;
    }

    public BooleanProperty getOneOneProperty() {
        return oneOne;
    }

    public StringProperty getBarcodeProperty() {
        return barcode;
    }

    public LongProperty getExpirationDateProperty() {
        return expirationDate;
    }

}
