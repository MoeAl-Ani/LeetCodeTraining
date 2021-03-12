use std::collections::HashSet;
use std::iter::FromIterator;

pub fn three_sum(nums: Vec<i32>) -> Vec<Vec<i32>> {

    if nums.len() < 3 {
        return vec![];
    }

    let mut set: HashSet<Vec<i32>> = HashSet::new();
    let mut nums = nums;
    nums.sort();

    let mut res:Vec<Vec<i32>> = vec![];
    for i in 0..nums.len() {
        for j in i+1..nums.len() {
            nums.get(i).unwrap() + nums.get(j).unwrap();
            match  nums.binary_search(&(-(nums.get(i).unwrap() + nums.get(j).unwrap()))){
                Ok(k) => {
                    let a = nums.get(i).unwrap();
                    let b = nums.get(j).unwrap();
                    let c = nums.get(k).unwrap();
                    if k > j {
                        let r = vec![*a,*b,*c];
                        if !set.contains(&r) {
                            set.insert(r);
                            //println!("({},{},{}) = 0", a, b, c);
                        }
                    }
                }
                Err(_) => {

                }
            }
        }
    }
    if set.is_empty(){
        return vec![];
    }
    for x in set.into_iter() {
        res.push(x);
    }
    res
}


#[cfg(test)]
mod tests {
    use crate::three_sum::three_sum;

    #[test]
    fn it_works() {
        let v : Vec<i32> = Vec::new();
        assert_eq!(vec![vec![-1,-1,2], vec![-1,0,1]], three_sum(vec![-1,0,1,2,-1,-4]));
    }
}