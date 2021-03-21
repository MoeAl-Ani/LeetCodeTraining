use std::collections::{HashSet, HashMap, VecDeque};
use std::iter::FromIterator;
use std::cell::RefCell;
use std::hash::{Hash, Hasher};
use std::rc::{Rc, Weak};
use std::borrow::{BorrowMut, Borrow};

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

    let mut graph = Graph::new(&word_list);
    for i in 0..word_list.len() {
        for j in 0..word_list.len() {
            if (i > j || i < j) && is_one_char_diff(word_list.get(i).unwrap(), word_list.get(j).unwrap()) {
                graph.add_edge(word_list.get(i).unwrap(), word_list.get(j).unwrap());
            }
        }
    }

    let shortest_path = ShortestPath::new(&mut graph, &begin_word);
    shortest_path.count_path_to(&end_word) as i32
}

pub struct Graph {
    pub vertices: HashMap<String, Rc<RefCell<Vertex>>>,
}

impl Graph {
    pub fn new(word_list: &Vec<String>) -> Self {
        let mut vertices = HashMap::new();
        let mut adj = vec![];
        for index in 0..word_list.len() {
            let data = word_list.get(index).unwrap();
            let vertex = Vertex::new(
                index,
                data,
            );
            vertices.insert(data.clone(), Rc::new(RefCell::new(vertex)));
            let mut set: HashSet<usize> = HashSet::new();
            adj.push(RefCell::new(set));
        };
        Graph {
            vertices
        }
    }

    pub fn add_edge(&mut self, i: &String, j:&String) {
        let pi = self.vertices.get(i).unwrap();
        let pj = self.vertices.get(j).unwrap();
        let mut x1 = pi.as_ref().borrow_mut();
        let mut x2 = pj.as_ref().borrow_mut();
        x1.adj.push(Rc::clone(pj));
        x2.adj.push(Rc::clone(pi));
    }
}

pub struct Vertex {
    id: usize,
    data: String,
    dist: usize,
    parent: Option<Weak<RefCell<Vertex>>>,
    visited: bool,
    adj: Vec<Rc<RefCell<Vertex>>>
}

impl Vertex {
    pub fn new(id: usize, data: &String) -> Self {
        Vertex {
            id,
            data: data.clone(),
            parent: None,
            visited: false,
            dist: 0,
            adj: vec![]
        }
    }
}

impl Hash for Vertex {
    fn hash<H: Hasher>(&self, state: &mut H) {
        self.id.hash(state);
    }
}


impl PartialEq for Vertex {
    fn eq(&self, other: &Self) -> bool {
        self.id == other.id
    }
}

impl Eq for Vertex {}

pub struct ShortestPath<'a> {
    graph: &'a mut Graph
}

impl <'a> ShortestPath<'a> {
    pub fn new(g: &'a mut Graph, src: &String) -> Self {
        if g.vertices.contains_key(src) {
            let vertex = g.vertices.get(src).unwrap();
            let mut queue = VecDeque::new();
            {
                let mut vertex = vertex.as_ref().borrow_mut();
                vertex.visited = true;
                queue.push_front(vertex.data.clone());
            }

            while !queue.is_empty() {
                let u = queue.pop_back().unwrap();
                let x = g.vertices.get(&u).unwrap();
                let u = x.as_ref().borrow_mut();
                for w in u.adj.iter() {
                    let mut w = w.as_ref().borrow_mut();
                    if !w.visited {
                        w.visited = true;
                        w.dist = u.dist.clone() + 1;
                        w.parent = Some(Rc::downgrade(x));
                        queue.push_front(w.data.clone());
                    }
                };
            }
        }
        ShortestPath {
            graph: g
        }
    }
    pub fn count_path_to(&self, dest: &String) -> usize {
        return if let Some(d) = self.graph.vertices.get(dest) {
            let v = d.as_ref().borrow();
            if v.dist > 0 {
                return v.dist + 1;
            }
            0
        } else { 0 }
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