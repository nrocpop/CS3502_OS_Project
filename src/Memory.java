public class  Memory {
    private byte[] storage;

   public byte load(int address){
       return storage[address];
   }
   public void store(byte value, int address){
       storage[address] = value;
   }

    public Memory(int size){
        storage = new byte[size];
    }
}
