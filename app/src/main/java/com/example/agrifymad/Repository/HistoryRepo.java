package com.example.agrifymad.Repository;

import com.example.agrifymad.models.HistoryModel;

import java.util.ArrayList;

public class HistoryRepo {
    private static HistoryRepo historyRepo;
    private  ArrayList<HistoryModel> historyModelList = new ArrayList<>();

    public HistoryRepo() {
        historyModelList.add(new HistoryModel("BananaMan", "Delivered successfully", "RM 12", "Yesterday", "https://www.schemecolor.com/images/color-image-thumb.php?tx&n=Nyanza&w=250&h=250&hex=DEF6DF"));
        historyModelList.add(new HistoryModel("Mint Leaf-Jude's Farm", "Self-collected", "RM 8", "Thursday", "https://www.schemecolor.com/images/color-image-thumb.php?tx&n=Nyanza&w=250&h=250&hex=DEF6DF"));
        historyModelList.add(new HistoryModel("Mango-Gold Susu", "Delivered successfully", "RM 23", "Wednesday", "https://www.schemecolor.com/images/color-image-thumb.php?tx&n=Nyanza&w=250&h=250&hex=DEF6DF"));
        historyModelList.add(new HistoryModel("Pineapple-MD2", "Delivered successfully", "RM 52", "A week ago", "https://www.schemecolor.com/images/color-image-thumb.php?tx&n=Nyanza&w=250&h=250&hex=DEF6DF"));
    }
    public static HistoryRepo getHistoryRepo(){
        if(historyRepo == null){
            historyRepo = new HistoryRepo();
        }
        return historyRepo;
    }

    public ArrayList<HistoryModel> getHistoryModelList() {
        return historyModelList;
    }
}