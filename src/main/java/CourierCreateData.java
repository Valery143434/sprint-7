public class CourierCreateData {
    public static CourierCreateDeserialization getNewCourier() {
        return new CourierCreateDeserialization("Валерий", "1234", "saske");
    }
    public static CourierCreateDeserialization getNewCourierSameLogin() {
        return new CourierCreateDeserialization("Валерий", "1233", "saska");
    }
    public static CourierCreateDeserialization getCourierWithoutLogin(){
        return new CourierCreateDeserialization(null, "1234", "saske");
    }
    public static CourierCreateDeserialization getCourierWithoutPassword(){
        return new CourierCreateDeserialization("Валерий", null, "Pavel");
    }
    public static CourierCreateDeserialization getCourierWithoutName(){
        return new CourierCreateDeserialization("Валерий", "1234", null);
    }
}
