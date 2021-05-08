package model;

    public abstract class Schedule {
        private int appointmentId;
        private String title;
        private long description;
        private String location;
        private String contact;
        private String type;
        private String startDate;
        private String startTime;
        private String endDate;
        private String endTime;
        private int customerId;

        public Schedule(){
        }

        public Schedule(int appointmentId, String title, long description, String location, String contact, String type, String startDate, String startTime, String endDate, String endTime, int customerId){
            this.appointmentId=appointmentId;
            this.title=title;
            this.description=description;
            this.location=location;
            this.contact=contact;
            this.type=type;
            this.startDate=startDate;
            this.startTime=startTime;
            this.endDate=endDate;
            this.endTime=endTime;
            this.customerId=customerId;
        }

        /**
         * @return the appointmentId
         */
        public int getAppointmentId(){
            return appointmentId;
        }

        /**
         * @param appointmentId the id to set
         */
        public void setAppointmentId(int appointmentId){
            this.appointmentId=appointmentId;
        }

        /**
         * @return the title
         */
        public String getTitle(){
            return title;
        }

        /**
         * @param title the of appointment
         */
        public void setName(String title){
            this.title=title;
        }

        /**
         * @return the description
         */
        public long getDescription(){
            return description;
        }

        /**
         * @param description for appointment
         */
        public void setPrice(long description){
            this.description=description;
        }

        /**
         * @return the location
         */
        public String getLocation(){
            return location;
        }

        /**
         * @param location of the appointment
         */
        public void setLocation(String location){
            this.location=location;
        }

        /**
         * @return the contact
         */
        public String getContact(){
            return contact;
        }

        /**
         * @param contact of the appointment
         */
        public void setContact(String contact){
            this.contact=contact;
        }

        /**
         * @return the type
         */
        public String getType(){
            return type;
        }

        /**
         * @param type for appointment
         */
        public void setType(String type){
            this.type=type;
        }

        /**
         *
         */
         public String getStartDate(){
            return startDate;
         }

        /**
         *
         * @param startDate
         */
         public void setStartDate(String startDate){
             this.startDate=startDate;
         }
        /**
         *
         *
         *
         */
        public String getStartTime(){
            return startTime;
        }
        /**
         *
         * @param startTime
         */
        public void setStartTime(String startTime){
            this.startTime=startTime;
        }

        /**
         *
         *
         *
         */
        public String getEndDate(){
            return endDate;
        }

        /**
         *
         * @param endDate
         */
        public void setEndDate(String endDate){
            this.endDate=endDate;
        }

        /**
         *
         *
         *
         */
        public String getEndTime(){
            return endTime;
        }
        /**
         *
         * @param endTime
         */
        public void setEndTime(String endTime){
            this.endTime=endTime;
        }

        /**
         *
         *
         *
         */
        public int getCustomerId(){
            return customerId;
        }

        /**
         *
         * @param customerId
         */
        public void setCustomerId(int customerId){
            this.customerId=customerId;
        }

}
