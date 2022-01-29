package Behavioral.Iterator.Code;

import java.util.ArrayList;

public class Friendlist implements IterableCollection {
    private ArrayList<String> friends = new ArrayList<String>();

    public void addFriend(String friend) {
        this.friends.add(friend);
    }

    // Implementation of Iterator interface
    @Override
    public Iterator createIterator() {
        return new FriendlistIterator();
    }

    // Concrete implementation of the iterator
    private class FriendlistIterator implements Iterator {
        private int position = 0;

        @Override
        public boolean hasNext() {
            return (position < friends.size());
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return friends.get(position++);
            }
            else {
                return null;
            }
        }
    }
}
