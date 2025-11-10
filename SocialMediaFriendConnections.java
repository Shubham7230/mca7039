import java.util.*;

class FriendNode {
    int friendId;
    FriendNode next;
    FriendNode(int friendId) {
        this.friendId = friendId;
        this.next = null;
    }
}

class UserNode {
    int userId;
    String name;
    int age;
    FriendNode friendList;
    UserNode next;
    UserNode(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendList = null;
        this.next = null;
    }
}

class SocialNetwork {
    UserNode head = null;

    void addUser(int userId, String name, int age) {
        UserNode newUser = new UserNode(userId, name, age);
        if (head == null) head = newUser;
        else {
            UserNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newUser;
        }
    }

    UserNode searchUserById(int userId) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    UserNode searchUserByName(String name) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int userId1, int userId2) {
        UserNode u1 = searchUserById(userId1);
        UserNode u2 = searchUserById(userId2);
        if (u1 == null || u2 == null) return;
        addFriendToList(u1, userId2);
        addFriendToList(u2, userId1);
    }

    void addFriendToList(UserNode user, int friendId) {
        FriendNode newFriend = new FriendNode(friendId);
        if (user.friendList == null) user.friendList = newFriend;
        else {
            FriendNode temp = user.friendList;
            while (temp.next != null) temp = temp.next;
            temp.next = newFriend;
        }
    }

    void removeFriend(int userId1, int userId2) {
        UserNode u1 = searchUserById(userId1);
        UserNode u2 = searchUserById(userId2);
        if (u1 == null || u2 == null) return;
        removeFriendFromList(u1, userId2);
        removeFriendFromList(u2, userId1);
    }

    void removeFriendFromList(UserNode user, int friendId) {
        FriendNode curr = user.friendList, prev = null;
        while (curr != null) {
            if (curr.friendId == friendId) {
                if (prev == null) user.friendList = curr.next;
                else prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    void displayFriends(int userId) {
        UserNode user = searchUserById(userId);
        if (user == null) {
            System.out.println("User not found");
            return;
        }
        System.out.print(user.name + "'s Friends: ");
        FriendNode temp = user.friendList;
        if (temp == null) {
            System.out.println("No friends");
            return;
        }
        while (temp != null) {
            UserNode f = searchUserById(temp.friendId);
            if (f != null) System.out.print(f.name + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    void mutualFriends(int userId1, int userId2) {
        UserNode u1 = searchUserById(userId1);
        UserNode u2 = searchUserById(userId2);
        if (u1 == null || u2 == null) {
            System.out.println("User not found");
            return;
        }
        System.out.print("Mutual Friends of " + u1.name + " and " + u2.name + ": ");
        FriendNode f1 = u1.friendList;
        boolean found = false;
        while (f1 != null) {
            FriendNode f2 = u2.friendList;
            while (f2 != null) {
                if (f1.friendId == f2.friendId) {
                    UserNode mutual = searchUserById(f1.friendId);
                    if (mutual != null) {
                        System.out.print(mutual.name + " ");
                        found = true;
                    }
                }
                f2 = f2.next;
            }
            f1 = f1.next;
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    void countFriends() {
        UserNode temp = head;
        while (temp != null) {
            int count = 0;
            FriendNode f = temp.friendList;
            while (f != null) {
                count++;
                f = f.next;
            }
            System.out.println(temp.name + " has " + count + " friends.");
            temp = temp.next;
        }
    }

    void displayAllUsers() {
        UserNode temp = head;
        while (temp != null) {
            System.out.println("User ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaFriendConnections {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialNetwork sn = new SocialNetwork();
        sn.addUser(1, "Virat", 21);
        sn.addUser(2, "Ram", 22);
        sn.addUser(3, "Tarun", 23);
        sn.addUser(4, "Arun", 24);

        sn.addFriend(1, 2);
        sn.addFriend(1, 3);
        sn.addFriend(2, 3);
        sn.addFriend(3, 4);

        sn.displayAllUsers();
        sn.displayFriends(1);
        sn.displayFriends(3);
        sn.mutualFriends(1, 3);
        sn.countFriends();

        sn.removeFriend(1, 3);
        System.out.println("\nAfter removing friendship between Alice and Charlie:");
        sn.displayFriends(1);
        sn.displayFriends(3);
    }
}
