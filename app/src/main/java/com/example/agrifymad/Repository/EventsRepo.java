package com.example.agrifymad.Repository;

import com.example.agrifymad.models.EventModel;

import java.util.ArrayList;

public class EventsRepo {
    private static EventsRepo eventsRepo;
    private  ArrayList<EventModel> eventModelList = new ArrayList<>();

    public EventsRepo() {
        eventModelList.add(new EventModel("24", "DEC", "Christmas Bazaar",  "https://static.dw.com/image/59622833_605.jpg"));
        eventModelList.add(new EventModel("05", "DEC", "Year End SALE",  "https://images.ctfassets.net/17o2epk8ivh7/4XOfcVjU6L9Z0yxkgW0WeI/95c8ab19d73e18671e9475de046233d4/DISCOUNT_TACTICS_NEW_COLOURS_FOR_VANESSA.jpg"));
        eventModelList.add(new EventModel("15", "NOV", "Papaya Season is Back!",  "https://images.lifestyleasia.com/wp-content/uploads/sites/5/2022/03/28121309/pexels-alleksana-4113835.jpg"));
        eventModelList.add(new EventModel("24", "OCT", "John's Farm is Open Today", "https://cdn.friendsoftheearth.uk/sites/default/files/styles/media_with_text_image/public/media/images/d8%20farming%20hay%20in%20field%20frosty%20blue.png?itok=n120JnpO"));
    }
    public static EventsRepo getEventsRepo(){
        if(eventsRepo == null){
            eventsRepo = new EventsRepo();
        }
        return eventsRepo;
    }

    public ArrayList<EventModel> getEventModelList() {
        return eventModelList;
    }
}