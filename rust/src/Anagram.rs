use std::collections::{HashMap};

pub fn group_anagrams(strs: Vec<String>) -> Vec<Vec<String>> {
    let mut res = vec![];
    let mut map: HashMap<String, Vec<String>> = HashMap::new();
    for word in strs {
        let mut chars = word.clone().chars().collect::<Vec<char>>();
        chars.sort_by(|c1,c2| c1.cmp(c2));

        let sorted_word = chars.into_iter().collect::<String>();

        if !map.contains_key(&sorted_word) {
            map.insert(sorted_word.clone(), vec![]);
        }
        let mut sub = map.get_mut(&sorted_word).unwrap();
        sub.push(word.clone());
    }
    for x in map.into_iter() {
        res.push(x.1);
    }
    res
}

#[cfg(test)]
mod tests {
    use crate::Anagram::group_anagrams;

    #[test]
    fn it_works() {
        println!("{:?}", group_anagrams(vec!["eat".to_owned(),"tea".to_owned(),"tan".to_owned(),"ate".to_owned(),"nat".to_owned(),"bat".to_owned()]));
        println!("{:?}", group_anagrams(vec!["".to_owned()]));
        println!("{:?}", group_anagrams(vec!["a".to_owned()]));
        println!("{:?}", group_anagrams(vec!["".to_owned(), "a".to_owned()]));
        println!("{:?}", group_anagrams(vec!["ddddddddddg".to_owned(),"dgggggggggg".to_owned()]));
    }
}