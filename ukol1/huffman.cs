using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using System.Diagnostics;

namespace Huffman
{
	class Node
    {
		private Node leftChild = null;
		private Node rightChild = null;
		private int frequency;
		private byte symbol;

		public Node(byte symbol, int frequency) 
		{            
			this.symbol = symbol;
			this.frequency = frequency;
		}

		public Node(Node leftChild, Node rightChild)
		{
			this.frequency = leftChild.frequency + rightChild.frequency;
			this.symbol = leftChild.symbol;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		public int GetFrequency() {
			return frequency;
		}

		public byte GetSymbol() {
			return symbol;
		}

		public Node GetRightChild() {
			return rightChild;
		}

		public Node GetLeftChild() {
			return leftChild;
		}

        /// <summary>
        /// Kdyz nema jedineho syna vraci true.
        /// </summary>
        /// <returns></returns>
		public bool IsLeaf()
		{
			return (leftChild == null) && (rightChild == null);
		}

		public static int SumWeight(Node prvni, Node druhy) // NOTE: nepouziva se
        {
            return prvni.GetFrequency() + druhy.GetFrequency();
        }

		/// <summary  >
		/// Zvetsi vahu vrcholu o zadany int, vraci upraveny Node.
		/// </summary>
		/// <param name="frequency"></param>
		/// <returns></returns>
		public Node IncreaseFrequency(int frequency) // NOTE: nepouziva se
		{
			this.frequency += frequency;
			return this;
		}
    }

    class Tree
    {
		private Node root = null;

		public Tree(String source)
		{
			Dictionary<byte, int> frequencies = createFrequenciesFromSource(source);
			List<Node> nodes = createFrequencyNodes(frequencies);
			build(nodes); // TODO: divny nazev - spatne se cte - build nodes? alternativy buildTree? this.build? ...
        }

		private Dictionary<byte, int> createFrequenciesFromSource(String source)
		{
			Dictionary<byte, int> frequencies = new Dictionary<byte, int>();

			for (int i = 0; i < source.Length; i++)
			{
				byte symbol = (byte)source[i];
			
				if (!frequencies.ContainsKey(symbol))
				{
					frequencies.Add(symbol, 0);
				}

				frequencies[symbol]++;
			}

			return frequencies;
        }


		private List<Node> createFrequencyNodes(Dictionary<byte, int> frequencies)
		{            
			List<Node> nodes = new List<Node>();//

			foreach (KeyValuePair<byte, int> symbol in frequencies)
			{
				nodes.Add(new Node(symbol.Key, symbol.Value));
			}

			return nodes;
		}

		private void build(List<Node> nodes)
		{
 			while (nodes.Count > 1)
			{
				List<Node> orderedNodes = nodes.OrderBy(node => node.getFrequency()).ThenBy(node => node.getSymbol()).ToList<Node>();

 				if (orderedNodes.Count >= 2)
				{
					List<Node> taken = orderedNodes.Take(2).ToList<Node>();
					Node parent = new Node(taken[0], taken[1]);

					nodes.Remove(taken[0]);
					nodes.Remove(taken[1]);
					nodes.Add(parent);
 				}
			}

			this.root = nodes.FirstOrDefault();
        }

        public void print()
        {
			if (this.root != null)
			{
				print2(this.root, "");
			}
		}

		private void print2(Node node, string pre)
		{
 			if (node.IsLeaf())
			{
				printLeaf (node);     
				//TODO: printLeaf nepouziva pre, je to korektni? nema printInner volat opet printInner? ...
            }
            else
            {
				printInner (node, pre);
            } 
        }

		private void printLeaf(Node node)
		{
			if ((node.GetSymbol () >= 32) && (node.GetSymbol () <= 0x7E)) { // fce s podminkou a nahradit cisla charem
				Console.Write (" ['{0}':{1}]\n", (char)node.GetSymbol (), node.GetFrequency ());
			} else {
				Console.Write (" [{0}:{1}]\n", node.GetSymbol (), node.GetFrequency ());
			} 
		}

		private void printInner (Node node, string pre)
		{
			Console.Write ("{0,4} -+- ", node.getFrequency ());

			pre = pre + "      ";
			print2 (node.GetRightChild (), pre + "|  ");
			Console.Write ("{0}|\n", pre);
			Console.Write ("{0}`- ", pre);
			print2 (node.GetLeftChild (), pre + "   ");
		}
	}

	class Loader
	{
		public static String sourceFromFile(string fileName)
		{
			String source = "";

 			try
			{
				using (StreamReader sr = new StreamReader(fileName))
				{
					source = sr.ReadToEnd();
				}
 			}
 			catch (Exception e)
			{
				Program.Error ("File Error");
            }

            return source;
        }

    }

    class Program
    {        
		public static void Error(string message)
		{
			Console.Write(message);
			Environment.Exit(0);
		}
        //   static Stopwatch sw = new Stopwatch();

        static void Main(string[] args)
        {                 
            //     sw.Start();

            if (args.Length != 1)
            {
				Program.Error ("Argument Error");
            }

            String source = Loader.sourceFromFile(args[0]);

            if (source.Length > 0)
            {
				Tree huffmanTree = new Tree(source);
				// TODO: print se vola jen zde - doplnit Console.Write("\n") do print nebo nasledujici radky do private metody Program
                Console.Write("\n");
                huffmanTree.print();
                Console.Write("\n");
            }

            /*      sw.Stop();
                  string ExecutionTimeTaken = string.Format("Minutes :{0}\nSeconds :{1}\n Mili seconds :{2}", sw.Elapsed.Minutes, sw.Elapsed.Seconds, sw.Elapsed.TotalMilliseconds);
                  Console.Write(ExecutionTimeTaken);
                  Console.ReadKey();
*/
			Console.ReadKey(); 
        }
    }
}
