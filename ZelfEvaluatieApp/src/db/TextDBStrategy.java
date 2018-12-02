package db;


public abstract class TextDBStrategy implements DBStrategy {


    // TEMPLATE METHOD
    @Override
    public void loadFromStorage() {
        setupConnectionToStorage();
        getDataFromStorage();
        setDataInLocalMemory();
    }


    // TEMPLATE METHOD
    @Override
    public void saveToStorage() {
        setupConnectionToStorage();
        setDataToStorage();
    }



    public abstract void setupConnectionToStorage();

    public abstract void getDataFromStorage();

    public abstract void setDataToStorage();

    public abstract void setDataInLocalMemory();

}
