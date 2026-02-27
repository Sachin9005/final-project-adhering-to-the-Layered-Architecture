package lk.ijse.carrentn.bo;

import lk.ijse.carrentn.bo.custom.impl.*;

    public class BOFactory {
        public static BOFactory boFactory;

        private BOFactory() {
        }

        public static BOFactory getInstance() {
            return boFactory == null ? new BOFactory() : boFactory;
        }

        public enum BOType {
            CAR_OWNER, CUSTOMER, VEHICLE, DISCOUNT, RENTAL, USER, PAYMENT, DRIVER, RENTAL_DISCOUNT;
        }

        public SuperBO getBO(BOType BOType) {
            switch (BOType) {
                case CAR_OWNER:
                    return new CarOwnerBOimpl();
                case CUSTOMER:
                    return new CustomerBOimpl();
                case VEHICLE:
                    return new VehicleBOimpl();
                case DISCOUNT:
                    return new DiscountBOimpl();
                case RENTAL:
                    return new RentalBOimpl();
                case USER:
                    return new UserBOimpl();
                case PAYMENT:
                    return new PaymentBOimpl();
                case DRIVER:
                    return new DriverBOimpl();
                case RENTAL_DISCOUNT:
                    return new RentalDiscountBOimpl();
                default:
                    return null;
            }
        }
    }

