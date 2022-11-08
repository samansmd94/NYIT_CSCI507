"""
@Group no: Group 2 (Hao, Sam)
@Time    : 2022-10-04 14:22 a.m.
@StudentID: 1313045, 1314389
"""


class Node():
    
    def __init__(self, element, next=None):
        self._element = element
        self._next = next

    def have_next(self):
        return self._next is not None

    @property
    def next(self):
        return self._next

    @property
    def element(self):
        return self._element

    def add_node(self, node):
        self._next = node


class SingleLinkedList():
    
    def __init__(self, name):
        self._head = None
        self._len = 0
        self._name = name

    @property
    def head(self):
        return self._head

    def __len__(self):
        return self._len

    def add_tail(self, element:int):
        if self._head is None:
            self._head = Node(element)
        else:
            pointer = self._head
            while pointer.have_next():
                pointer = pointer.next
            pointer.add_node(Node(element))

        self._len+=1

    def iterate_print(self):
        print(f"{self._name}:", end="", flush=True)
        if self._head is None:
            print("no elements in the linkedlist")
        else:
            pointer = self._head
            while True:
                print(f"{pointer.element}", end="", flush=True)
                pointer = pointer.next
                if pointer is None:
                    break
                else:
                    print("->", end="", flush=True)
            print()
                

if __name__ == '__main__':
    
    singleLinkedList1 = SingleLinkedList("linklist1")
    singleLinkedList1.add_tail(8)
    singleLinkedList1.add_tail(18)
    singleLinkedList1.add_tail(6)
    singleLinkedList1.add_tail(3)
    singleLinkedList1.add_tail(2)
    singleLinkedList1.add_tail(8)
    singleLinkedList1.iterate_print()

    singleLinkedList2 = SingleLinkedList("linklist2")
    singleLinkedList2.add_tail(8)
    singleLinkedList2.add_tail(18)
    singleLinkedList2.add_tail(1)
    singleLinkedList2.add_tail(6)
    singleLinkedList2.add_tail(3)
    singleLinkedList2.add_tail(2)
    singleLinkedList2.add_tail(8)
    singleLinkedList2.iterate_print()

    # Calculate alignment position based on length
    len1 = len(singleLinkedList1)
    len2 = len(singleLinkedList2)
    len_delta = len1 - len2

    print(f"Then length of link1 is:{len1} link2 is:{len2}")

    pointer1:Node = singleLinkedList1.head
    pointer2:Node = singleLinkedList2.head
    link1_skipped_elements = 0
    link2_skipped_elements = 0

    if len_delta>0:
        print(f"link1's head pointer move {len_delta} steps backward")
        for i in range(len_delta):
            pointer1 = pointer1.next
            link1_skipped_elements += 1
    elif len_delta<0:
        print(f"link2's head pointer move {-len_delta} steps backward")
        for i in range(-len_delta):
            pointer2 = pointer2.next
            link2_skipped_elements += 1

    intersection_ptr_1 = None

    while True:

        if pointer1.element == pointer2.element:
            if intersection_ptr_1 is None:
                intersection_ptr_1 = pointer1
        else:
            if intersection_ptr_1 is not None:
                link1_skipped_elements += 1
                link2_skipped_elements += 1
            intersection_ptr_1 = None
            link1_skipped_elements += 1
            link2_skipped_elements += 1

        pointer1 = pointer1.next
        pointer2 = pointer2.next
        if pointer1 is None:
            break

    # print the result
    if intersection_ptr_1 is None:
        print("no intersection")
    else:
        print(f"Intersection exist shown below. Linklist1 skip {link1_skipped_elements} elements, linklist2 skip {link2_skipped_elements} elements.")
        while True:
            print(f"{intersection_ptr_1.element}", end="", flush=True)
            intersection_ptr_1 = intersection_ptr_1.next
            if intersection_ptr_1 is None:
                break
            else:
                print("->", end="", flush=True)
        print()