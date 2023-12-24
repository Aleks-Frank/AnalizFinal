package org.example;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserFull;

import java.util.List;

public class VkApiRepor {
    private final int APP_ID = 0;
    private final String CODE = "my_CODE";
    private final VkApiClient vk;
    private final UserActor actor;

    public VkApiRepor() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public int getUserIdByName(String name) {
        try {
            List<UserFull> users = vk.users().search(actor)
                    .q(name)
                    .execute()
                    .getItems();

            if (!users.isEmpty()) {
                return users.get(0).getId();
            }
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getUserCity(int userId) {
        if (userId != 0) {
            try {
                var userProfile = vk.users().get(actor)
                        .userIds(String.valueOf(userId))
                        .fields(Fields.CITY)
                        .execute()
                        .get(0);

                if (userProfile.getCity() != null) {
                    System.out.println(userProfile.getCity().getTitle());
                    return userProfile.getCity().getTitle();
                } else {
                    System.out.println("-");
                    return "Unknown";
                }

            } catch (ApiException | ClientException e) {
                e.printStackTrace();
            }

            return null;
        } else {
            return null;
        }
    }
}