pub fn three_sum_closest(nums: Vec<i32>, target: i32) -> i32 {
    if nums.len() < 3 {
        return 0;
    }
    let mut nums = nums;
    nums.sort();
    let mut closest = nums.get(0).unwrap() + nums.get(1).unwrap() + nums.get(2).unwrap();


    for i in 0..nums.len() {
        let mut left = i+1;
        let mut right = nums.len() -1;

        while left < right {
            let sum = nums.get(i).unwrap() + nums.get(left).unwrap() + nums.get(right).unwrap();
            if (sum - target).abs() < (closest - target).abs() {
                closest = sum;
            }

            if sum < target {
                left+=1;
            } else {
                right-=1;
            }
        }
    }
    closest
}

#[cfg(test)]
mod tests {
    use crate::three_sum_closest::three_sum_closest;

    #[test]
    fn it_works() {
        assert_eq!(2, three_sum_closest(vec![-1,2,1,-4], 1));
    }
}