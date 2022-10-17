
"""
@Group no: Group 2 (Hao, Sam)
@Time    : 2022-09-20 15:22 a.m.
@StudentID: 1313045, 1314389
"""

from typing import List
from prettytable import PrettyTable


class Tower():

    def __init__(self, name:str):
        self._name = name

        self._capacity = 100
        self._stack: List[int] = []

        self._top = None

    @property
    def name(self)->str:
        return self._name

    @property
    def size(self)->int:
        return len(self._stack)

    def pop(self) -> int:
        if self._top is None:
            raise Exception(f"{self.name} underflow")

        top_disk = self._stack[self._top]
        self._stack.pop()
        if self._top == 0:
            self._top = None
        else:
            self._top -= 1
            assert (self._top == len(self._stack)-1)

        return top_disk

    def push(self, disk: int):
        if self._top == self._capacity-1:
            raise Exception((f"{self.name} overflow"))

        if not self.empty() and disk >= self.peek():
            raise Exception((f"{self.name} you can't push a bigger one to this tower"))

        self._stack.append(disk)
        if self._top is None:
            self._top = 0
        else:
            self._top += 1
        assert (self._top == len(self._stack)-1)

    def empty(self) -> bool:
        return self._top is None

    def peek(self) -> int:
        if self._top is None:
            raise Exception("underflow")

        return self._stack[self._top]

    def show_current_disks(self):
        print(self._stack)

    def get_stack_buffer(self, target_size:int)->List[str]:
        buffer = []
        buffer.extend(["-"*item for item in self._stack ])
        for i in range(target_size-len(self._stack)):
            buffer.append("")
        buffer.reverse()
        return buffer

class Player():
    
    def __init__(self, disk_count:int):
        self._disk_count = disk_count

        self._init_variables()

        print("Initial Status")
        self.show_status()

    def _init_variables(self):
        self._steps = 0

        self._source_tower = Tower("SourceTower")
        self._destination_tower = Tower("DestinationTower")
        self._temp_tower = Tower("TempTower")

        # initiate source tower with disks
        for i in range(self._disk_count,0,-1):
            self._source_tower.push(i)


    def run(self):
        self._init_variables()

        self._play_tower_of_hanoi(self._disk_count, self._source_tower, self._destination_tower,self._temp_tower)

        print(f"I totally used {self._steps} steps. Final status below:")
        self.show_status()

    def _play_tower_of_hanoi(self, disk_counts:int, source_tower:Tower, destination_tower:Tower, temp_tower:Tower):
        
        if disk_counts == 0:
            return

        self._play_tower_of_hanoi(disk_counts - 1, source_tower, temp_tower, destination_tower);
        destination_tower.push(source_tower.pop())
        self._steps+=1
        print(f"Step{self._steps}: {destination_tower.peek()} has moved from {source_tower.name} to {destination_tower.name}, status below:")
        self.show_status()
        self._play_tower_of_hanoi(disk_counts - 1, temp_tower, destination_tower, source_tower);

    def show_status(self):

        max_size = max(self._source_tower.size, self._destination_tower.size, self._temp_tower.size)

        table = PrettyTable()
        table.add_column('Source Tower', self._source_tower.get_stack_buffer(max_size))
        table.add_column('Temp Tower', self._temp_tower.get_stack_buffer(max_size))
        table.add_column('Destination Tower', self._destination_tower.get_stack_buffer(max_size))

        print(table)
    

if __name__ == '__main__':

    disk_count = 3

    player = Player(disk_count)
    player.run()

        
