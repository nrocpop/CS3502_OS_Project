//Loader loads programs into disk;
public class Disk {
    private byte[] storage;

    public byte load(int address){
        return storage[address];
    }
    public void store(byte value, int address){
        storage[address] = value;
    }

    public Disk(int size){
        storage = new byte[size];
    }
}

