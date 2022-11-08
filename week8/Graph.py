"""
@Group no: Group 2 (Hao, Sam)
@Time    : 2022-11-04 14:22 a.m.
@StudentID: 1313045, 1314389
"""
from typing import List
import pandas as pd
import numpy as np

class Vertice():

    def __init__(self, name:str):
        self.name:str = name
        self.adjacency_lists:List[str] = []

    def add_adjacency(self, adjacencies:str):
        for adjacency in adjacencies:
            self.adjacency_lists.append(adjacency)

    def show(self):
        print(f"{self.name}:{self.adjacency_lists}")

class Graph():
    
    def __init__(self):
        
        self.vertices:List[Vertice] = []

        self._df:pd.DataFrame = None

    def construct(self):

        print("\nBelow is the adjacency lists:")

        self.vertices.append(Vertice("A"))
        self.vertices[-1].add_adjacency("BH")
        self.vertices.append(Vertice("B"))
        self.vertices[-1].add_adjacency("AHC")
        self.vertices.append(Vertice("C"))
        self.vertices[-1].add_adjacency("BIDF")
        self.vertices.append(Vertice("D"))
        self.vertices[-1].add_adjacency("CFE")
        self.vertices.append(Vertice("E"))
        self.vertices[-1].add_adjacency("DF")
        self.vertices.append(Vertice("F"))
        self.vertices[-1].add_adjacency("CDEG")
        self.vertices.append(Vertice("G"))
        self.vertices[-1].add_adjacency("HIF")
        self.vertices.append(Vertice("H"))
        self.vertices[-1].add_adjacency("ABIG")
        self.vertices.append(Vertice("I"))
        self.vertices[-1].add_adjacency("HCG")

    def show(self):

        for vertice in self.vertices:
            vertice.show()

    def calculate_edges(self):
        print("-"*30)
        print("Edges:",int(self._df.sum().sum()/2))

    def generate_matrix(self):
        
        my_index = [i.name for i in self.vertices]

        print(my_index)

        self._df = pd.DataFrame(np.zeros((len(self.vertices), len(self.vertices))), 
                                columns=my_index, index=my_index, dtype=np.int)

        for vertice in self.vertices:
            for adjacency in vertice.adjacency_lists:
                self._df .loc[vertice.name, adjacency] = 1

        print("-"*30)
        print("Below is the adjacency matrix that is also symmetrical.")
        print(self._df )

        df_1 = self._df .transpose()
        assert (self._df ==df_1).sum().sum()==self._df.size, "Error: This matrix is not a symmetric matrix."


if __name__ == '__main__':
    
    graph = Graph()
    graph.construct()
    graph.show()
    graph.generate_matrix()
    graph.calculate_edges()