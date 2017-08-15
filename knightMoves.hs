import Data.List
import Data.Maybe
import Control.Applicative

type KnightPos = (Int,Int)

-- Taken from Learn You A Haskell For Great Good!
moveKnight :: KnightPos -> [KnightPos]
moveKnight (c, r) = filter onBoard
    [(c+2,r-1),(c-2,r-1),(c+2,r+1),(c-2,r+1)
    ,(c-1,r+2),(c-1,r-2),(c+1,r-2),(c+1,r+2)
    ]
    where onBoard (c, r) = c `elem` [1..8] && r `elem` [1..8]

-- Rest is my own code

-- Recursive application of moveKnight, and concatenates the result into a single list
moveKnightList :: [KnightPos] -> [KnightPos]
moveKnightList [] = []
moveKnightList (x:xs) = (moveKnight x) ++ (moveKnightList xs)

-- Fail if start and end are the same tile;
-- otherwise, find the first element of the infinite list of move sequences that
-- contains the desired tile, adding 1 to give the correct number of moves
movesToReach :: KnightPos -> KnightPos -> Int
movesToReach start end
    | start == end = 0
    | otherwise    = fromMaybe 0 $ (+) <$> Just 1 <*> (findIndex (elem end) (iterate moveKnightList (moveKnight start)))

-- Main program
-- Coordinates must be entered like so: "col row"
main = do
    putStr "c1 r1 = "
    start <- getLine
    putStr "c2 r2 = "
    end <- getLine
    let startpos = map read $ words start
        endpos = map read $ words end
        n = movesToReach (startpos !! 0, startpos !! 1) (endpos !! 0, endpos !! 1)
        in putStrLn $ "\nThe number of moves required to get from " ++ show startpos ++ " to " ++ show endpos ++ " is " ++ show n ++ ".\n"