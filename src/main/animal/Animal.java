package main.animal;

    public class Animal {
        private String image;
        private int count;

        public Animal(String image) {
            this.image = image;
            this.count = 0;
        }

        public String getImage() {
            return image;
        }

        public int getCount() {
            return count;
        }

        public void add(int number) {
            count += number;
        }

        public void remove(int number) {
            count = Math.max(0, count - number);
        }

        @Override
        public String toString() {
            return image + " (" + count + ")";
        }
       // public void move(int[][]){
         //   if()
       // }
    }