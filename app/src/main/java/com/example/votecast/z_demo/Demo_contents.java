package com.example.votecast.z_demo;

import com.example.votecast.R;
import com.example.votecast.models.Chat;
import com.example.votecast.models.ChatUser;
import com.example.votecast.models.CoinPlan;
import com.example.votecast.models.Comment;
import com.example.votecast.models.GiftCategory;
import com.example.votecast.models.GiftRoot;
import com.example.votecast.models.LiveStramComment;
import com.example.votecast.models.Post;
import com.example.votecast.models.Reels;
import com.example.votecast.models.Song;
import com.example.votecast.models.Sticker;
import com.example.votecast.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Demo_contents {


    public static ArrayList<String> girlsImage = new ArrayList<>(Arrays.asList("https://images.unsplash.com/photo-1506610154363-2e1a8c573d2d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=844&q=80",
            "https://images.unsplash.com/photo-1555703473-5601538f3fd8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=858&q=80",
            "https://images.unsplash.com/photo-1597983073453-ef06cfc2240e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=880&q=80",
            "https://images.unsplash.com/photo-1588671335940-2a6646b6bb0a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=8getRandomPostCoint()&q=80",
            "https://images.unsplash.com/photo-1583058905141-deef2de746bb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=888&q=80"

    ));

    public static List<Sticker> getStickers() {
        List<Sticker> stickers = new ArrayList<>();
        stickers.add(new Sticker(1, "https://muly.starthub.ltd/storage/demo/stickers/tBYh155Uj846jNB.png"));
        stickers.add(new Sticker(2, "https://muly.starthub.ltd/storage/demo/stickers/5xjouRhyJJul6vG.png"));
        stickers.add(new Sticker(3, "https://muly.starthub.ltd/storage/demo/stickers/VQsIiRGJb1xyR29.png"));
        stickers.add(new Sticker(4, "https://muly.starthub.ltd/storage/demo/stickers/uMupGAtXaI2Yzm6.png"));
        stickers.add(new Sticker(5, "https://muly.starthub.ltd/storage/demo/stickers/6MRpnln3q8DMTuC.png"));
        stickers.add(new Sticker(6, "https://muly.starthub.ltd/storage/demo/stickers/r6oSVjkVNY9Opww.png"));
        stickers.add(new Sticker(7, "https://muly.starthub.ltd/storage/demo/stickers/rcKJ3JIuBT6JQkL.png"));
        stickers.add(new Sticker(8, "https://muly.starthub.ltd/storage/demo/stickers/vtJsNlyEUZvqEQb.png"));
        stickers.add(new Sticker(9, "https://muly.starthub.ltd/storage/demo/stickers/dvRToewsl0vliMw.png"));
        stickers.add(new Sticker(10, "https://muly.starthub.ltd/storage/demo/stickers/9N2gUIUDdwTsPAT.png"));

        return stickers;
    }

    public static ArrayList<String> boysImage = new ArrayList<>(Arrays.asList(
            "https://ibb.co/bLvNRkH",
            "https://i.ibb.co/924y37w/yagiz.jpg",
            "https://ibb.co/bLvNRkH",
            "https://ibb.co/bLvNRkH",
            "https://ibb.co/bLvNRkH",
            "https://ibb.co/bLvNRkH"
    ));

    public static List<Song> getSongFiles() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Rahogi Meri", "Pritam, Arijit Singh", "https://muly.starthub.ltd/storage/demo/covers/BydL9iUJ1wRZAgYpng", "https://muly.starthub.ltd/storage/demo/audios/jrmyRx4Uwy3GkVy.aac", 14, ""));

        songs.add(new Song(2, "Coca Cola", "Pritam, Arijit Singh",
                "https://muly.starthub.ltd/storage/demo/covers/ZFpka7K6dxUAQCnpng",
                "https://muly.starthub.ltd/storage/demo/audios/jrmyRx4Uwy3GkVy.aac", 19, ""));

        songs.add(new Song(3, "Savage Love (Laxed - Siren Beat)", "Jawsh 685, Jason Derulo",
                "https://muly.starthub.ltd/storage/demo/covers/ZEybCPyhf0QcUZZpng",
                "https://muly.starthub.ltd/storage/demo/audios/93BahZERK0DOiiq.aac", 28, ""));

        songs.add(new Song(4, "Thumbi Thullal", "A. R. Rahman",
                "https://muly.starthub.ltd/storage/demo/covers/pU59tYWwgzC6Hi5png",
                "https://muly.starthub.ltd/storage/demo/audios/S3XXGz6YoTWwvaZ.aac", getRandomPostCoint(), ""));

        songs.add(new Song(5, "For the Night", "Pop Smoke, Lil Baby & DaBaby",
                "https://muly.starthub.ltd/storage/demo/covers/6XyPuIdF3PJmEICpng",
                "https://muly.starthub.ltd/storage/demo/audios/93BahZERK0DOiiq.aac", getRandomPostCoint(), ""));


        return songs;
    }

    public static List<String> girlsBio() {
        List<String> bios = new ArrayList<>();

        String bio1 = "Money can’t buy happiness. But it can buy Makeup!";
        String bio2 = "Sometimes it’s the princess who kills the dragon and saves the prince.";
        String bio3 = "love..dancing.\uD83D\uDE18\uD83D\uDE18\n" +
                "luv ❤my❤ friends\uD83D\uDC48";
        String bio4 = "\uD83D\uDCF7Like Photography✔\n" +
                "\uD83D\uDC01Animal Lover✔\n" +
                "\uD83C\uDF6CChocolate Lover✔\n" +
                "\uD83D\uDE2DFirst Cry On 11th March✔\n" +
                "\uD83D\uDC8AMedical Student✔\n";
        String bio5 = "I’m a princess \uD83D\uDC96, not because I have a Prince, but because my dad is a king \uD83D\uDC51\n";

        bios.add(bio1);
        bios.add(bio2);
        bios.add(bio3);
        bios.add(bio4);
        bios.add(bio5);

        return bios;
    }

    public static List<String> boysBio() {
        List<String> bios = new ArrayList<>();

        String bio1 = "\uD83D\uDCAFOfficial account\uD83D\uDD10\n" +
                "\uD83D\uDCF7Photography\uD83D\uDCF7\n" +
                "\uD83D\uDE18Music lover\uD83C\uDFB6\n" +
                "⚽Sports lover⛳\n" +
                "\uD83D\uDE0DSports bike lover\n";
        String bio2 = "\uD83D\uDC51Attitude Prince\uD83D\uDC51\n" +
                "\uD83E\uDD1DDosto Ki Shan\uD83D\uDC9C\n" +
                "\uD83D\uDC8FGF Ki Jaan♥️\n" +
                "\uD83D\uDC9EMom Ka Ladla\uD83D\uDC93\n" +
                "\uD83D\uDC99Pappa Ka Pyara\uD83D\uDC99";
        String bio3 = "love..dancing.\uD83D\uDE18\uD83D\uDE18\n" +
                "luv ❤my❤ friends\uD83D\uDC48";
        String bio4 = "\uD83D\uDCF7Like Photography✔\n" +
                "\uD83D\uDC01Animal Lover✔\n" +
                "\uD83C\uDF6CChocolate Lover✔\n" +
                "\uD83D\uDE2DFirst Cry On 11th March✔\n" +
                "\uD83D\uDC8AMedical Student✔\n";
        String bio5 = "༺❉MR. Perfect❉༻\n" +
                "\uD83D\uDCA5King OF 22 May\uD83C\uDF1F\n" +
                "\uD83C\uDFB5Music Addicted\uD83C\uDFB6\n" +
                "\uD83D\uDC9C Photography\uD83D\uDCF8\n" +
                "\uD83D\uDC95Heart Hã¢Kër\uD83D\uDC8C";

        bios.add(bio1);
        bios.add(bio2);
        bios.add(bio3);
        bios.add(bio4);
        bios.add(bio5);

        return bios;
    }

    public static List<User> getUsers(boolean isShuffle) {


        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Antonio", boysBio().get(0), "@aaliya1", "aaliyamia@email.com", boysImage.get(0), "Berlin, Deutschland",
                        1, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "MALE"),

                new User("Yagiz", boysBio().get(1), "@Lily", "Lily@email.com", boysImage.get(1), "Berlin, Deutschland",
                        2, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "MALE"),

                new User("Antonio", girlsBio().get(2), "@Charlotte", "Bailey@email.com", girlsImage.get(2), "Berlin, Deutschland",
                        3, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "FEMALE"),

                new User("Yagiz", girlsBio().get(3), "@Isabella", "Kennedy@email.com", girlsImage.get(3), "Berlin, Deutschland",
                        4, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "FEMALE"),

                new User("Antonio", girlsBio().get(4), "@Camila", "Marshall@email.com", girlsImage.get(4), "Berlin, Deutschland",
                        5, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "FEMALE"),
                //,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

                new User("Antonio", boysBio().get(0), "@Carter", "James@email.com", boysImage.get(0), "Berlin, Deutschland",
                        1, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "MALE"),

                new User("Yagiz", boysBio().get(1), "@Henry", "Adams@email.com", boysImage.get(1), "Berlin, Deutschland",
                        2, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "MALE"),

                new User("Antonio", boysBio().get(2), "@Davidson", "Daniel@email.com", boysImage.get(2), "Berlin, Deutschland",
                        3, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "MALE"),

                new User("Yagiz", boysBio().get(3), "@Jackson", "Edwards@email.com", boysImage.get(3), "Berlin, Deutschland",
                        4, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "MALE"),

                new User("Antonio", boysBio().get(4), "@Thomas", "Bailey@email.com", boysImage.get(4), "Berlin, Deutschland",
                        5, getRandomCoin(), getRandomCoin(), getRandomPostCoint(), getRandomPostCoint(), getRandomCoin(), getRandomPostCoint(), getRandomCoin(), "MALE")

        ));
        return users;
    }

    public static int getRandomCoin() {
        Random random = new Random();
        int i = random.nextInt(1000 - 111) + 111;
        return i;
    }

    public static int getRandomPostCoint() {
        Random random = new Random();
        int i = random.nextInt(100 - 11) + 11;
        return i;
    }

    public static List<LiveStramComment> getLiveStreamComment() {
        List<LiveStramComment> liveStramComments = new ArrayList<>();

        liveStramComments.add(new LiveStramComment("", getUsers(true).get(0), true));
        liveStramComments.add(new LiveStramComment("", getUsers(true).get(0), true));
        liveStramComments.add(new LiveStramComment("", getUsers(true).get(0), true));
        liveStramComments.add(new LiveStramComment("", getUsers(true).get(0), true));
        liveStramComments.add(new LiveStramComment("", getUsers(true).get(0), true));
        liveStramComments.add(new LiveStramComment("Please stop looking so hot every time.", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Looking very very hot\uD83D\uDD25in summer", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Your queenly smiles are what my eyes have been longing to see.", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Too hot for me to handle", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Every single part of your body was made according to my spec.", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("I drop my cap for you.", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Your hotness is just beating me everytim.", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Classy shot and awesome background too.", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Hello dear,", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("Give me your mobile number", getUsers(true).get(0), false));
        liveStramComments.add(new LiveStramComment("9975537455 it is my mobile number", getUsers(true).get(0), false));
        Collections.shuffle(liveStramComments);
        return liveStramComments;

    }

    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();

        posts.add(new Post("Mitzeichnungsfrist: 03.03.2022", "Letzte Anpassung: 16.10.2021", "Mit der Petition wird gefordert, dass Direktmandate für den Bundestag begrenzt, verringert und von den Wahlkreisen entkoppelt werden. Damit wird in Phase 1 erreicht, dass nur die Wahlkreise ein Direktmandat erhalten, die über genügend eindeutige Akzeptanz über die Erststimme erhalten haben. Dies soll über 2 Faktoren erreicht werden:\n" +
                "1) FG = Faktor für Gesamt-Akzeptanz, z. B. mind. 1/3 aller gültigen Stimmen\n" +
                "2) FR = Faktor Relative-Akzeptanz, z. B. mind. 1/3 Stimmen mehr als der Zweitplatzierte",
                ""
                , getUsers(false).get(0), getRandomPostCoint(), getRandomPostCoint()));
        posts.add(new Post("Mitzeichnungsfrist: 17.02.2022", "Letzte Anpassung: 11.02.2022", "Nur durch Errichtung eines nationalen deutschen Impfregisters ist es der Bundesrepublik Deutschland möglich, die Corona-Epidemie bestmöglich zu bekämpfen, um damit den größtmöglichen Schaden von dem deutschen Volk abzuwenden.",
                ""
                , getUsers(false).get(1), getRandomPostCoint(), getRandomPostCoint()));
        posts.add(new Post("Rome Italy", "Letzte Anpassung: 23.11.2021", "If you don’t believe in yourself, who will?",
                ""
                , getUsers(false).get(2), getRandomPostCoint(), getRandomPostCoint()));
        posts.add(new Post("Rome Italy", "Letzte Anpassung: 23.11.2021", "If you don’t believe in yourself, who will?",
                ""
                , getUsers(false).get(3), getRandomPostCoint(), getRandomPostCoint()));
        //Collections.shuffle(posts);
        return posts;
    }

    public static List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment(getUsers(true).get(0), "2 hour ago", "No matter where I go, I cannot find someone beautiful like you."));
        comments.add(new Comment(getUsers(true).get(0), "Just Now", "❤️Your looks make me insane.❤️"));
        comments.add(new Comment(getUsers(true).get(0), "5 hour ago", "Such a charming capture✔️✔️✔️✔️"));
        comments.add(new Comment(getUsers(true).get(0), "12 hour ago", "Well I think this is often my favorite posture of yours"));
        comments.add(new Comment(getUsers(true).get(0), "2 minutes ago", "\uD83D\uDC9BHow can somebody be this beautiful\uD83D\uDC9B"));
        comments.add(new Comment(getUsers(true).get(0), "5 minutes ago", "❤️We all are favored to see your magnificence.❤️"));
        comments.add(new Comment(getUsers(true).get(0), "22 May 2021", "This is amazing."));
        comments.add(new Comment(getUsers(true).get(0), "2 Sep 2021", "Omg!! your look steals my heart"));
        comments.add(new Comment(getUsers(true).get(0), "12 Oct 2021", "⊂◉‿◉つ\n" +
                "– \\ \uD83D\uDC49 \\ \uD83D\uDC49 Nice.. pics Miss\n" +
                "\uD83D\uDC49This is Awesome Pic\n" +
                "\uD83D\uDC49 by \uD83C\uDF37 Anurag \uD83C\uDF37"));


        Collections.shuffle(comments);
        return comments;
    }

    public static List<Reels> getReels() {

        List<Reels> reels = new ArrayList<>();

        reels.add(new Reels(getUsers(true).get(0), "Creativity is my thing, what's yours?", "https://dev.digicean.com/storage/239891192_3061111304125653_779170672042824055_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "Creativity is my thing, what's yours?", "https://dev.digicean.com/storage/241681828_894992998067015_8734076864715268332_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "Life is better when you're laughing.", "https://dev.digicean.com/storage/242025003_129510119414535_2313267097805870250_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "If you like this video, it's supposed to bring you good luck for the rest of the day.", "https://dev.digicean.com/storage/242025003_129510119414535_2313267097805870250_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "If life had a soundtrack, this would be my song.", "https://dev.digicean.com/storage/247062863_341024567823973_5333055093789086258_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "I reel-y got into this whole Instagram Reels thing.", "https://dev.digicean.com/storage/254729432_1108864849935754_4352344584780102107_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "I'm not going to tell you how long it took me to edit this.", "https://dev.digicean.com/storage/255030357_230359622521878_8463246573248024146_n.mp4", getRandomPostCoint(), getRandomPostCoint()));

        reels.add(new Reels(getUsers(true).get(0), "It's me, the best dancer on your feed.", "https://dev.digicean.com/storage/247062863_341024567823973_5333055093789086258_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "If I’m on your feed, you know it’s gonna be a good day.", "https://dev.digicean.com/storage/241681828_894992998067015_8734076864715268332_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "Wedding", "https://dev.digicean.com/storage/255030357_230359622521878_8463246573248024146_n.mp4", getRandomPostCoint(), getRandomPostCoint()));
        reels.add(new Reels(getUsers(true).get(0), "Beauty of Bride", "https://dev.digicean.com/storage/239891192_3061111304125653_779170672042824055_n.mp4", getRandomPostCoint(), getRandomPostCoint()));


        Collections.shuffle(reels);
        return reels;
    }

    public static List<ChatUser> getChatUsers() {
        List<ChatUser> chatUsers = new ArrayList<>();

        chatUsers.add(new ChatUser(getUsers(false).get(0), "jetzt gerade", "Hey Ken"));
        chatUsers.add(new ChatUser(getUsers(false).get(1), "jetzt gerade", "Hi"));
        chatUsers.add(new ChatUser(getUsers(false).get(2), "jetzt gerade", "Hey Ken"));
        chatUsers.add(new ChatUser(getUsers(false).get(3), "jetzt gerade", "Hi"));
        chatUsers.add(new ChatUser(getUsers(false).get(4), "jetzt gerade", "Hey Ken"));
        chatUsers.add(new ChatUser(getUsers(false).get(5), "jetzt gerade", "Hi"));
        chatUsers.add(new ChatUser(getUsers(false).get(6), "jetzt gerade", "Hey Ken"));
        chatUsers.add(new ChatUser(getUsers(false).get(7), "jetzt gerade", "Hi"));
        chatUsers.add(new ChatUser(getUsers(false).get(8), "jetzt gerade", "Hey Ken"));
        chatUsers.add(new ChatUser(getUsers(false).get(9), "jetzt gerade", "Hi"));

        Collections.shuffle(chatUsers);
        return chatUsers;
    }

    public static List<Chat> getRandomChat() {
        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat("What's yor name ?", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Hey do you want chat with me ?", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Hmm", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("What ?", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Are you kidding with me?", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Do  you have  girlfriend?", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("hey Dude I am boring now \n so you can chat with me", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("I am busy right now \n talk to you later", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Send me your insta id", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Send me your Number", Chat.TEXT, Chat.USER1));
        chats.add(new Chat("Am i looking Sexy??", Chat.TEXT, Chat.USER1));


        //Collections.shuffle(chats);
        return chats;
    }

    public static List<CoinPlan> getCoinList() {
        List<CoinPlan> coinPlans = new ArrayList<>();
        coinPlans.add(new CoinPlan(100, 10, ""));
        coinPlans.add(new CoinPlan(200, 20, ""));
        coinPlans.add(new CoinPlan(1000, 90, "10% off"));
        coinPlans.add(new CoinPlan(10000, 800, "20% off"));
        coinPlans.add(new CoinPlan(50000, 2500, "50% off"));
        return coinPlans;
    }

    public static List<String> getFemaleVideos() {
        List<String> videos = new ArrayList<>(Arrays.asList(
                "https://dev.digicean.com/storage/1614063597527.mp4",
                "https://dev.digicean.com/storage/1%20(14).mp4",
                "https://dev.digicean.com/storage/1%20(22).mp4",
                "https://dev.digicean.com/storage/1%20(5).mp4",
                "https://dev.digicean.com/storage/1%20(4).mp4"
        ));
        Collections.shuffle(videos);
        return videos;
    }

    public static List<String> getHashtags() {
        List<String> videos = new ArrayList<>(Arrays.asList(
                "#Love", "#Nature", "#Wedding", "#Alone", "#Female", "#Chill", "#Beauty", "#Life", "#Honeymoon", "#Style", "#Happy", "#Smile", "#Music", "#Sunset", "#Sport"
        ));
        Collections.shuffle(videos);
        return videos;
    }

    public static List<String> getLocations() {
        List<String> videos = new ArrayList<>(Arrays.asList(
                "Berlin"
        ));
        Collections.shuffle(videos);
        return videos;
    }

    public static List<GiftCategory> getGiftCategory() {
        List<GiftRoot> emoji = new ArrayList<>(Arrays.asList(
                new GiftRoot(1, R.raw.emoji, 10, GiftRoot.IMAGE),
                new GiftRoot(2, R.raw.emoji1, 10, GiftRoot.IMAGE),
                new GiftRoot(3, R.raw.emoji2, 10, GiftRoot.IMAGE),
                new GiftRoot(6, R.raw.party, 10, GiftRoot.IMAGE),
                new GiftRoot(7, R.raw.star, 10, GiftRoot.IMAGE),
                new GiftRoot(8, R.raw.wink, 10, GiftRoot.IMAGE),
                new GiftRoot(9, R.raw.wow, 10, GiftRoot.IMAGE)


        ));
        List<GiftRoot> love = new ArrayList<>(Arrays.asList(
                new GiftRoot(5, R.raw.heart, 10, GiftRoot.IMAGE),
                new GiftRoot(16, R.raw.s116, 10, GiftRoot.IMAGE),
                new GiftRoot(13, R.raw.s113, 10, GiftRoot.IMAGE),
                new GiftRoot(14, R.raw.s114, 10, GiftRoot.IMAGE),
                new GiftRoot(18, R.raw.s118, 10, GiftRoot.IMAGE),
                new GiftRoot(33, R.raw.srose1, 10, GiftRoot.IMAGE)


        ));
        List<GiftRoot> sticker = new ArrayList<>(Arrays.asList(
                new GiftRoot(4, R.raw.g_fox, 10, GiftRoot.IMAGE),
                new GiftRoot(10, R.raw.s110, 10, GiftRoot.IMAGE),
                new GiftRoot(11, R.raw.s111, 10, GiftRoot.IMAGE),
                new GiftRoot(15, R.raw.s115, 10, GiftRoot.IMAGE),
                new GiftRoot(17, R.raw.s117, 10, GiftRoot.IMAGE),
                new GiftRoot(19, R.raw.s119, 10, GiftRoot.IMAGE),
                new GiftRoot(20, R.raw.s120, 10, GiftRoot.IMAGE),
                new GiftRoot(12, R.raw.s112, 10, GiftRoot.IMAGE),
                new GiftRoot(21, R.raw.s121, 10, GiftRoot.IMAGE),
                new GiftRoot(22, R.raw.s122, 10, GiftRoot.IMAGE),
                new GiftRoot(23, R.raw.s123, 10, GiftRoot.IMAGE),
                new GiftRoot(24, R.raw.s124, 10, GiftRoot.IMAGE),
                new GiftRoot(25, R.raw.s125, 10, GiftRoot.IMAGE),
                new GiftRoot(26, R.raw.s126, 10, GiftRoot.IMAGE),
                new GiftRoot(27, R.raw.s127, 10, GiftRoot.IMAGE),
                new GiftRoot(28, R.raw.s128, 10, GiftRoot.IMAGE),
                new GiftRoot(29, R.raw.s129, 10, GiftRoot.IMAGE),
                new GiftRoot(30, R.raw.srose, 10, GiftRoot.IMAGE),
                new GiftRoot(31, R.raw.s130, 10, GiftRoot.IMAGE),
                new GiftRoot(32, R.raw.s131, 10, GiftRoot.IMAGE)


        ));
        Collections.shuffle(love);
        Collections.shuffle(emoji);
        Collections.shuffle(sticker);

        List<GiftCategory> giftCategories = new ArrayList<>();
        giftCategories.add(new GiftCategory("Sticker", sticker));
        giftCategories.add(new GiftCategory("Emoji", emoji));
        giftCategories.add(new GiftCategory("Love", love));

        //sticker

        // Collections.shuffle(list);
        return giftCategories;
    }

}
