use std::collections::{HashSet, HashMap, VecDeque};
use std::iter::FromIterator;
use std::cell::RefCell;

pub fn ladder_length(begin_word: String, end_word: String, word_list: Vec<String>) -> i32 {
    if begin_word.len() == 0 {
        return 0;
    }
    if end_word.len() == 0 {
        return 0;
    }
    if begin_word.eq(&end_word) {
        return 0;
    }
    if !word_list.contains(&end_word) {
        return 0;
    }
    let mut word_list = word_list;
    if !word_list.contains(&begin_word) {
        word_list.insert(0, begin_word.clone());
    }

    let mut graph = Graph::new(word_list.len());
    for i in 0..word_list.len() {
        for j in 0..word_list.len() {
            if (i > j || i < j) && is_one_char_diff(word_list.get(i).unwrap(), word_list.get(j).unwrap()) {
                graph.add_edge(&i, &j, &word_list);
            }
        }
    }

    let shortest_path = ShortestPath::new(&graph, &begin_word);
    shortest_path.count_path_to(&end_word) as i32
}

pub struct Graph {
    pub adj: Vec<RefCell<HashSet<usize>>>,
    pub v: usize,
    pub w_map: HashMap<String, usize>
}

impl Graph {
    pub fn new(vertices: usize) -> Self {
        let mut adj = vec![];
        for _ in 0..vertices {
            let mut set: HashSet<usize> = HashSet::new();
            adj.push(RefCell::new(set));
        };
        let mut w_map = HashMap::new();
        Graph {
            adj,
            v: vertices,
            w_map
        }
    }

    pub fn add_edge(&mut self, i: &usize, j:&usize, word_list: &Vec<String>) {
        self.w_map.insert(word_list.get(*i).unwrap().clone(), *i);
        self.w_map.insert(word_list.get(*j).unwrap().clone(), *j);
        let mut ass = self.adj.get(*i).unwrap().borrow_mut();
        ass.insert(*j);
        ass.insert(*i);
    }

    pub fn adj(&self, v: usize) -> &RefCell<HashSet<usize>> {
        self.adj.get(v).unwrap()
    }
}

pub struct ShortestPath<'a> {
    dist: Vec<usize>,
    pred: Vec<Option<usize>>,
    visited: Vec<bool>,
    w_map: &'a HashMap<String, usize>
}

impl <'a>ShortestPath<'a> {
    pub fn new(g: &'a Graph, src: &String) -> Self {
        let mut dist = vec![0; g.v];
        let mut pred = vec![None; g.v];
        let mut visited = vec![false; g.v];
        match g.w_map.get(src) {
            None => {
                ShortestPath {
                    dist,
                    pred,
                    visited,
                    w_map: &g.w_map
                }
            }
            Some(src_id) => {
                visited[*src_id] = true;

                let mut queue = VecDeque::new();
                queue.push_front(*src_id);

                while !queue.is_empty() {
                    let u = queue.pop_back().unwrap();
                    for w in g.adj.get(u).unwrap().borrow().iter() {
                        if !visited[*w] {
                            visited[*w] = true;
                            dist[*w] = dist[u] + 1;
                            pred[*w] = Some(u);
                            queue.push_front(*w);
                        }
                    };
                }
                ShortestPath {
                    dist,
                    pred,
                    visited,
                    w_map: &g.w_map
                }
            }
        }
    }
    pub fn count_path_to(&self, dest: &String) -> usize {
        return if let Some(d) = self.w_map.get(dest) {
            let distance = self.dist[*d];
            if distance > 0 {
                distance + 1
            } else {
                0
            }
        } else {
            0
        }
    }
}

pub fn is_one_char_diff(first: &String, second: &String) -> bool {
    if first.len() != second.len() {
        return false;
    };
    let size = first.len();
    let mut count = 0;
    for index in 0..size {
        if first.get(index..index+1) != second.get(index..index+1) {
            count+=1;
        }
    }
    if count > 1 || count == 0{
        return false;
    }
    true
}

#[cfg(test)]
mod tests {
    use crate::word_ladder::ladder_length;

    #[test]
    fn it_works() {
        assert_eq!(5, ladder_length("hit".to_owned(), "cog".to_owned(), vec!["hit".to_owned(), "hot".to_owned(), "dot".to_owned(), "dog".to_owned(), "lot".to_owned(), "log".to_owned(), "cog".to_owned()]));
        assert_eq!(2, ladder_length("a".to_owned(), "c".to_owned(), vec!["a".to_owned(), "b".to_owned(), "c".to_owned()]));
        assert_eq!(0, ladder_length("hot".to_owned(), "dog".to_owned(), vec!["hot".to_owned(), "dog".to_owned()]));
    }
}