# Iterator Design Pattern

## ❇️ Description:
Iterator is a behavioral design pattern that lets us traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc). It falls under **behavioral** pattern category. We use this pattern when we need to "abstractify" the traversal of wildly different data structures so that algorithms can be defined that are capable of interfacing with each transparently.



## ❇️ Problem
Suppose we're building a social media app where we can add friends. In our app, we need to have the ability to iterate over our friendlist. So we have to store all our friends in a data structure (i.e. array, list, tree, stack). For the sake of example, let's take a **fixed size array** to store all our friends.

Let's have a new class named `Friendlist` where we'll store our friends:
```java
public class Friendlist {
    // for sake of example, we're storing friends as a string. Irl, it'd be an object
    private String[] friends = new String[100];
    private int friendCount = 0;

    // getter
    public int getFriendCount() {return friendCount;}
    public String[] getFriends() {return friends;}

    // method for adding friends
    public void addFriend(String friend) {
        this.friends[this.friendCount] = friend;
        this.friendCount++;
    }
}
```
Now, in the `Main` class, we can iterate over our friendlist in the following manner:
```java
public class Main {
    public static void main(String[] args) {
        var friendlist = new Friendlist();

        friendlist.addFriend("John");
        friendlist.addFriend("Mark");
        friendlist.addFriend("Zucc");

        // this will iterate over the friendist and print the names
        for(int i=0; i<friendlist.getFriendCount(); i++) {
            System.out.println(friendlist.getFriends()[i]);
        }
    }
}
```


Then, one day we realized that a fixed array is probably not the right data structure for storing friends. We instead want to shift to a different data structure, like an arrayList. For that, we'll need to change our `Friendlist` class a bit:

```java
public class Friendlist {
    private ArrayList<String> friends = new ArrayList<String>();

    // getter
    public ArrayList getFriends() {return friends;}

    // method for adding and removing friends
    public void addFriend(String friend) {
        friends.add(friend);
    }
}
```

Till now, everything has been going smooth. But wait! The change we just made inside our `Friendlist` class has affected our driver code in the `Main` class as well, and now we have to deal with these breaking changes as well otherwise the code won't run.
So let's fix the driver code:
```java
public class Main {
    public static void main(String[] args) {
        var friendlist = new Friendlist();

        friendlist.addFriend("John");
        friendlist.addFriend("Mark");
        friendlist.addFriend("Zucc");

        for(int i=0; i<friendlist.getFriends().size(); i++) {
            System.out.println(friendlist.getFriends().get(i));
        }
    }
}
```

We are now faced with a problem. The change we made inside a class affected our codebase outside that class. This may not seem like a big issue in this small example, but **imagine if we had a gigantic codebase** and one change inside a class had a rippling effect across the whole codebase. It'd require a huge undertaking to fix the whole codebase, and in the long run, our software wouldn't be extensible at all. As a rule of thumb, we should always code in a manner so that a change inside a class never affects anything outside of that class. If we want to achieve that in this example, we'll have to "abstractify" our implementation of iteration.

_N.B. Since we're working with the iterator pattern, **our main focus will be on just the iteration of the friendlist**. The other functionalities of `Friendlist` (like adding friends) are not the primary concern of the iterator pattern._ 


