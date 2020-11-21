package ke.co.propscout.mobank.data.models;

import androidx.annotation.NonNull;

public class CustomerWithOwner {

    private String ownerId;

    public <T extends CustomerWithOwner> T withOwnerId(@NonNull final String ownerId) {
        this.ownerId = ownerId;
        return (T) this;
    }

    public String getOwnerId() {
        return ownerId;
    }
}
