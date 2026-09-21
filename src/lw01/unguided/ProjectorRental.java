public class ProjectorRental extends Rental {

    public ProjectorRental(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int rentalCost;
        if (days <= 3){
            rentalCost = days * 60000 + (30000 * getUnits());
        } else {
            rentalCost = (3 * 60000) + ((days - 3) * 45000) + (30000 * getUnits());
        }
        return rentalCost + 2000;
    }

    @Override
    public String label() {
        return "Projector";
    }
}