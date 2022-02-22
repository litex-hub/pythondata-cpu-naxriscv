plugins.collect{ case p : CommitPlugin  => p }.foreach{p => p.commitCount = 1 }
plugins.collect{ case p : AlignerPlugin  => p }.foreach{p => p.decodeCount = 1 }

